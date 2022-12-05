// install (please make sure versions match peerDependencies)
// yarn add @nivo/core @nivo/bar
import { ResponsiveBar } from "@nivo/bar";
import _useAxiosGet from "../_service/_useAxiosGet";

// make sure parent container have a defined height when using
// responsive component, otherwise height will be 0 and
// no chart will be rendered.
// website examples showcase many properties,
// you'll often use just a few of them.
export default function DashboardOrdersPage({ setUser, user }) {
  const url = "http://172.25.16.1:8080/api/dashboard/order";
  let newData = _useAxiosGet(url, setUser, user);
  let content = "";
  if (newData.error) {
    content = (
      <div>
        <div classNameName="">
          There was an error please refresh or try again later.
        </div>
      </div>
    );
  }

  if (newData.loading) {
    content = <p>Loading</p>;
  }

  if (newData.data) {
    const test = newData.data;
    content = (
      <ResponsiveBar
        data={test}
        keys={["quantity"]}
        indexBy="date"
        margin={{ top: 50, right: 0, bottom: 220, left: 30 }}
        padding={0.3}
        valueScale={{ type: "linear" }}
        indexScale={{ type: "band", round: true }}
        colors={{ scheme: "spectral" }}
        colorBy="index"
        borderColor={{ from: "color", modifiers: [["brighter", 1.6]] }}
        axisTop={null}
        axisRight={null}
        axisBottom={{
          tickSize: 5,
          tickPadding: 5,
          tickRotation: 0,
          legend: "Date",
          legendPosition: "middle",
          legendOffset: 32,
        }}
        axisLeft={{
          tickSize: 5,
          tickPadding: 5,
          tickRotation: 0,
          legend: "Number of orders",
          legendPosition: "middle",
          legendOffset: -40,
        }}
        labelSkipWidth={12}
        labelSkipHeight={12}
        labelTextColor={{ from: "color", modifiers: [["darker", 1.6]] }}
        role="application"
        ariaLabel="Nivo"
        barAriaLabel={function (e) {
          return (
            e.id + ": " + e.formattedValue + " in country: " + e.indexValue
          );
        }}
      />
    );
  }

  return (
    <>
      <section className="section-text-intro">
        <div className="shell">
          <header className="section__head" data-aos="zoom-in-up">
            <h1>Dashboard Orders</h1>
          </header>
        </div>
      </section>

      <section className="section-dashboard">
        <div className="shell">
          <div className="section__content">{content}</div>
        </div>
      </section>
    </>
  );
}

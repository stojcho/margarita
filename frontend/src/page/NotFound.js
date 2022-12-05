import { Link } from "react-router-dom";
export default function NotFound() {
  return (
    <div className="main">
      <section className="section-text-center">
        <div className="shell">
          <div className="section__content">
            <h1>Error 404 - Not Found</h1>

            <p>
              Please check the URL for proper spelling and capitalization. If
              you're having trouble locating a destination, try visiting the:
            </p>
          </div>

          <div className="section__actions">
            <Link to="home" className="btn">
              Homepage
            </Link>
          </div>
        </div>
      </section>
    </div>
  );
}

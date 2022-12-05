describe("The Login Page", () => {
  beforeEach(() => {
    cy.visit("/logout");
    cy.visit("/login");
  });
  it("successfully loads", () => {
    cy.get(".popup__head h2").should("be.visible");
    cy.get("input[name=user-uname]").should("be.visible");
    cy.get("input[name=user-password]").should("be.visible");
    cy.get(".popup__head h2").should("be.visible");
    cy.get(".form__actions a").should("be.visible");
  });

  it("validation message", () => {
    cy.get("#user-uname")
      .invoke("prop", "validationMessage")
      .should("equal", "Please fill in this field.");
    cy.get("input[name=user-password]")
      .invoke("prop", "validationMessage")
      .should("equal", "Please fill in this field.");
  });
});

describe("The Register Page", () => {
  beforeEach(() => {
    cy.visit("/logout");
    cy.visit("/register");
  });
  it("successfully loads", () => {
    cy.get(".popup__head h2").should("be.visible");
    cy.get("input[name=username]").should("be.visible");
    cy.get("input[name=email]").should("be.visible");
    cy.get("input[name=password]").should("be.visible");
    cy.get(".popup__head h2").should("be.visible");
    cy.get(".form__actions a").should("be.visible");
  });

  it("validation message", () => {
    cy.get("#username")
      .invoke("prop", "validationMessage")
      .should("equal", "Please fill in this field.");
    cy.get("#email")
      .invoke("prop", "validationMessage")
      .should("equal", "Please fill in this field.");
    cy.get("#password")
      .invoke("prop", "validationMessage")
      .should("equal", "Please fill in this field.");
  });
});

describe("Categories and single page load validation", () => {
  beforeEach(() => {
    cy.visit("/login");
    cy.get("#user-uname").clear();
    cy.get("#user-uname").type("admin");
    cy.get("#user-password").clear();
    cy.get("#user-password").type("admin1234");
    cy.get(".form__actions > .btn").click();
    cy.wait(1000);
  });
  afterEach(() => {
    cy.visit("/logout");
  });

  it("check if header text is correct", function () {//!!!
    cy.get(".nav-trigger > :nth-child(3)").click();
    cy.get(".nav-trigger").click();
    cy.get(".nav > ul > :nth-child(1) > a").click();
    cy.get("h1").should("have.text", "Basic Cakes");
    cy.get(".nav-trigger").click();
    cy.get(".nav > ul > :nth-child(2) > a").click();
    cy.get("h1").should("have.text", "Wedding Cakes");
    cy.get(".nav-trigger").click();
    cy.get(".nav > ul > :nth-child(3) > a").click();
    cy.get("h1").should("have.text", "Birthday Cakes");
    cy.get(".nav-trigger").click();
    cy.get(".nav > ul > :nth-child(4) > a").click();
    cy.get("h1").should("have.text", "Special Cakes");
  });

  it("successfully loads", function () {
    cy.get(":nth-child(2) > .cake > .cake__actions > .btn").click();
    cy.get("h1").should("be.visible");
    cy.get("h2").should("be.visible");
    cy.get(".section__entry > p").should("be.visible");
    cy.get(".section__entry > h4").should("be.visible");
    cy.get(".section__price > p").should("be.visible");
  });
});
describe("Account page", () => {
  beforeEach(() => {
    cy.visit("/logout");
    cy.visit("/login");
    cy.get("#user-uname").clear();
    cy.get("#user-uname").type("admin");
    cy.get("#user-password").clear();
    cy.get("#user-password").type("admin1234");
    cy.get(".form__actions > .btn").click();
    cy.wait(1000);
  });
  afterEach(() => {
    cy.visit("/logout");
  });
  it("successfully loads", function () {
    cy.get(":nth-child(1) > .account__btn > .btn").click({ force: true });
    cy.get("#name").should("be.visible");
    cy.get("#phone").should("be.visible");
    cy.get("#email").should("be.visible");
    cy.get("#street").should("be.visible");
    cy.get("#city").should("be.visible");
    cy.get("#streetNumber").should("be.visible");
    cy.get("#country").should("be.visible");
    cy.get("#zipCode").should("be.visible");
  });
});
describe("Add product page", () => {
  beforeEach(() => {
    cy.visit("/logout");
    cy.visit("/login");
    cy.get("#user-uname").clear();
    cy.get("#user-uname").type("admin");
    cy.get("#user-password").clear();
    cy.get("#user-password").type("admin1234");
    cy.get(".form__actions > .btn").click();
    cy.wait(1000);
  });
  afterEach(() => {
    cy.visit("/logout");
  });

  it("successfully loads", function () {
    cy.get(":nth-child(2) > .account__btn > .btn").click({ force: true });
    cy.get("#name").should("be.visible");
    cy.get("#category").should("be.visible");
    cy.get("#description").should("be.visible");
    cy.get("#price").should("be.visible");
    cy.get("#image").should("be.visible");
  });
  it("validation message", function () {
    cy.get(":nth-child(2) > .account__btn > .btn").click({ force: true });
    cy.wait(1000);
    cy.get("#name")
      .invoke("prop", "validationMessage")
      .should("equal", "Please fill in this field.");
    cy.get("#category")
      .invoke("prop", "validationMessage")
      .should("equal", "Please fill in this field.");
    cy.get("#description")
      .invoke("prop", "validationMessage")
      .should("equal", "Please fill in this field.");
    cy.get("#price")
      .invoke("prop", "validationMessage")
      .should("equal", "Please fill in this field.");
    cy.get("#image")
      .invoke("prop", "validationMessage")
      .should("equal", "Please select a file.");
  });
});
describe("Order page", () => {
  beforeEach(() => {
    cy.visit("/logout");
    cy.visit("/login");
    cy.get("#user-uname").clear();
    cy.get("#user-uname").type("admin");
    cy.get("#user-password").clear();
    cy.get("#user-password").type("admin1234");
    cy.get(".form__actions > .btn").click();
    cy.wait(1000);
  });
  afterEach(() => {
    cy.visit("/logout");
  });

  it("successfully loads", function () {
    cy.get(":nth-child(3) > .account__btn > .btn").click({ force: true });
    cy.get("h1").should("be.visible");
    cy.get("thead > tr > :nth-child(1)").should("be.visible");
    cy.get("thead > tr > :nth-child(2)").should("be.visible");
    cy.get("thead > tr > :nth-child(3)").should("be.visible");
    cy.get("thead > tr > :nth-child(4)").should("be.visible");
    cy.get("thead > tr > :nth-child(5)").should("be.visible");
    cy.get("thead > tr > :nth-child(6)").should("be.visible");
    cy.get("thead > tr > :nth-child(7)").should("be.visible");
    cy.get("thead > tr > :nth-child(8)").should("be.visible");
  });
});
describe("Dashboard pages", () => {
  beforeEach(() => {
    cy.visit("/logout");
    cy.visit("/login");
    cy.get("#user-uname").clear();
    cy.get("#user-uname").type("admin");
    cy.get("#user-password").clear();
    cy.get("#user-password").type("admin1234");
    cy.get(".form__actions > .btn").click();
    cy.wait(1000);
  });
  afterEach(() => {
    cy.visit("/logout");
  });

  it("successfully loads - product graph", function () {
    cy.get(":nth-child(4) > .account__btn > .btn").click({ force: true });
    cy.get("h1").should("have.text", "Dashboard Products");
    cy.get(".section-dashboard").should("be.visible");
  });

  it("successfully loads - order graph", function () {
    cy.get(":nth-child(5) > .account__btn > .btn").click({ force: true });
    cy.get("h1").should("have.text", "Dashboard Orders");
    cy.get(".section-dashboard").should("be.visible");
  });
});
describe("Create Order", () => {
  beforeEach(() => {
    cy.visit("/logout");
    cy.visit("/login");
    cy.get("#user-uname").clear();
    cy.get("#user-uname").type("admin");
    cy.get("#user-password").clear();
    cy.get("#user-password").type("admin1234");
    cy.get(".form__actions > .btn").click();
    cy.wait(1000);
  });
  afterEach(() => {
    cy.visit("/logout");
  });

  it("make the order", function () {
    //Add product to the cart
    cy.get(":nth-child(2) > .cake > .cake__image").click({ force: true });
    cy.get(
      ".header__actions > :nth-child(1) > :nth-child(2) > span > :nth-child(2)"
    ).should("have.text", "0 Items");
    cy.get(".section__actions-inner > .btn").click();
    cy.get(
      ".header__actions > :nth-child(1) > :nth-child(2) > span > :nth-child(2)"
    ).should("have.text", "1 Items");
    
    //Change quantity
    cy.get(
      ".header__actions > :nth-child(1) > :nth-child(2) > a > .fas"
    ).click();
    cy.get(".quantity__field").should('have.value', '1');
    cy.get(".quantity__add").click();
    cy.get(".quantity__field").should('have.value', '2');
    cy.get(".quantity__sub > .fas").click();
    cy.get(".quantity__field").should('have.value', '1');
    
    //Make order
    cy.get(".form__actions > .btn").click();
    cy.get("p > span").should("have.text", "0");
    cy.get(
      ".header__actions > :nth-child(1) > :nth-child(2) > span > :nth-child(2)"
    ).should("have.text", "0 Items");
  });
});

describe('Home', () => {
  beforeEach(() => {
    cy.visit('/');
  });

  context('Check integrity', () => {
    it('h1 is correct', () => {
      cy.queryByText("Hello World!").should('exist');
    });
  });
});

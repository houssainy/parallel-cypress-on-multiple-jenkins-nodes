describe('Test 2', () => {
  beforeEach(() => {
    cy.visit('/');
  });

  context('Check integrity', () => {
    it('Test #2', () => {
      cy.queryByText("Hello World!").should('not.exist');
    });
  });
});


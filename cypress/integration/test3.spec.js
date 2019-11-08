describe('Test 3', () => {
  beforeEach(() => {
    cy.visit('/');
  });

  context('Check integrity', () => {
    it('Test #2', () => {
      cy.queryByText("Hello yes").should('not.exist');
    });
  });
});


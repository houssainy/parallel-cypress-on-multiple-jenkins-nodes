def installYarn = """
${meteorRunner} npm install -g yarn

${meteorRunner} yarn install || error=true

#Fail the build if there was an error
if [ \$error ]
then
  rm -f yarn.lock
  ${meteorRunner} yarn install --skip-integrity-check
fi
"""


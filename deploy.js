const fs = require('fs');
const token = fs.readFileSync(`${__dirname}/.firebase-token`);
const projectName = (JSON.parse(fs.readFileSync(`${__dirname}/.firebaserc`, 'utf8'))).projects[process.env.NODE_ENV ? process.env.NODE_ENV : 'default'];
/**
 * FireBase Deployment script
 * FOR CI USE ONLY!
 */

console.log('Deploying to Firebase... please wait.');

require('firebase-tools')
    .deploy((() => ({project: projectName, token: token, cwd: __dirname}))())
.then(() => console.log(`Your project ${projectName} has been deployed`))
.catch((err) => console.warn(`error has occurred! - ${err}`));
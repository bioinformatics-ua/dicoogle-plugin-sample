// This build script updates the output package.json file.
//
// Although using the original package.json file works,
// it is better to provide a simple version to production,
// to reduce size and simplify the plugin.
const fs = require('fs');
const packageJson = require('./package.json');

const {
    name,
    version,
    private,
    description,
    license,
    dicoogle,
} = packageJson;

const out = {
    name,
    version,
    private,
    description,
    license,
    main: dicoogle['module-file'] || 'module.js',
    dicoogle,
};
fs.mkdirSync('dist', {recursive: true});
fs.writeFileSync("dist/package.json", JSON.stringify(out, undefined, 2));

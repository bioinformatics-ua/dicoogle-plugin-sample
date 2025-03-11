const Dicoogle = require('dicoogle-client')();

export default class MyPlugin {

    constructor() {
        // TODO initialize plugin here
    }

    /** 
     * @param {HTMLDivElement} parent dedicated container div for this plugin
     * @param {HTMLDivElement} slot the web UI plugin slot
     */
    render(parent, slot) {
        // create header
        const head = document.createElement('h3');
        head.innerText = 'Health Check';
        parent.appendChild(head);

        // create main info span
        const baseInfo = document.createElement('span');
        baseInfo.innerText = 'Checking Dicoogle health...';
        parent.appendChild(baseInfo);

        // request for the full list of plugins
        Dicoogle.getPlugins().then(({ plugins, dead }) => {
            const problems = [];

            // check for no storage
            if (plugins.filter(p => p.type === 'storage').length === 0) {
                problems.push("No storage providers are installed");
            }

            // check for no DICOM query provider
            if (plugins.filter(p => p.type === 'query' && p.dim).length === 0) {
                problems.push("No DICOM data query providers are installed");
            }

            // check for no DICOM index provider
            if (plugins.filter(p => p.type === 'index' && p.dim).length === 0) {
                problems.push("No DICOM data indexers are installed");
            }

            if (dead.length > 0) {
                problems.push("The following plugins are dead: " + dead
                    .map(p => `${p.name} (${p.cause.message})`).join(', '))
            }

            // update DOM with problems
            if (problems.length === 0) {
                baseInfo.innerText = "✓ No issues were found!";
            } else {
                baseInfo.innerText = `⚠️ There are ${problems.length} ${problems.length === 1 ? "issue" : "issues"} in this installation.`;

                // create list of issues found
                const ul = document.createElement('ul');
                for (const problem of problems) {
                    // one list item per problem
                    let problemItem = document.createElement('li');
                    problemItem.innerText = problem;
                    ul.appendChild(problemItem);
                }
                parent.appendChild(ul);
            }
        });
    }
}

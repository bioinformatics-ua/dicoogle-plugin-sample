var e=require("dicoogle-client");Object.defineProperty(module.exports,"__esModule",{value:!0,configurable:!0}),Object.defineProperty(module.exports,"default",{get:function(){return n},set:void 0,enumerable:!0,configurable:!0});const t=e();class n{constructor(){}render(e,n){let l=document.createElement("h3");l.innerText="Health Check",e.appendChild(l);let r=document.createElement("span");r.innerText="Checking Dicoogle health...",e.appendChild(r),t.getPlugins().then(({plugins:t,dead:n})=>{let l=[];if(0===t.filter(e=>"storage"===e.type).length&&l.push("No storage providers are installed"),0===t.filter(e=>"query"===e.type&&e.dim).length&&l.push("No DICOM data query providers are installed"),0===t.filter(e=>"index"===e.type&&e.dim).length&&l.push("No DICOM data indexers are installed"),n.length>0&&l.push("The following plugins are dead: "+n.map(e=>`${e.name} (${e.cause.message})`).join(", ")),0===l.length)r.innerText="✓ No issues were found!";else{r.innerText=`\u{26A0}\u{FE0F} There are ${l.length} ${1===l.length?"issue":"issues"} in this installation.`;let t=document.createElement("ul");for(let e of l){let n=document.createElement("li");n.innerText=e,t.appendChild(n)}e.appendChild(t)}})}}
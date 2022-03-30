const yaml = require('js-yaml');
const fs = require('fs');
const Path = require('path');
const glob = require('glob');

const javaSnippetPath = '/mnt/doc/**/snippets/*-java.test.yml';
const kotlinSnippetPath = '/mnt/doc/**/snippets/*-kotlin.test.yml';
const snippetTemplatesPath = '/mnt/snippet-templates';
const buildDir = '/mnt/build';

/**
 * Extract code from balises @start and @stop
 * If one of the balise is missing, the whole file is returned
 * 
 * @param {String} code 
 * @returns 
 */
function extractCode(code) {
  const lines = code.split('\n');
  const start = lines.findIndex(line => line.includes('// @start'));
  const end = lines.findIndex(line => line.includes('// @end'));

  if (start === -1 || end === -1) {
    return code;
  }

  return lines.slice(start, end).join('\n');
}

/**
 * Go over each file and build the snippet with the right template
 * then build the method that encapsulate the snippet
 * 
 * @param {String[]} paths 
 * @param {String} ext 
 * @param {*} templates 
 * @returns 
 */
function buildSnippetMethods(paths, ext, templates) {
  const methodTemplate = fs.readFileSync(`./templates/method.${ext}`, 'utf-8');

  const methods = {};
  for (const path of paths) {
    const testConfig = yaml.load(fs.readFileSync(path, 'utf8'));

    const template = templates[`${testConfig.template}.tpl.${ext}`];

    const snippetName = testConfig.name.toLowerCase().replace(/ /g, '_').replace(/-/g, '_').replace(/#/g,'');

    const snippetCode = extractCode(fs.readFileSync(path.replace('.test.yml', `.${ext}`), 'utf8'));

    const snippet = template.replace('[snippet-code]', snippetCode);
    methods[snippetName] = methodTemplate.replace('[snippet-name]', snippetName).replace('[snippet-code]', snippet);
  }

  return methods;
}

/**
 * Build each snippet in a static method and bundle every method in a single file
 * ready to be compiled
 * 
 * @param {*} paths 
 * @param {*} ext 
 * @param {*} templates 
 */
function bundleAndBuildTests(paths, ext, templates) {
  const methods = buildSnippetMethods(paths, ext, templates);

  const mainTemplate = fs.readFileSync(`./templates/main.${ext}`, 'utf8');

  const buildedSnippets = Object.keys(methods).map(key => methods[key]).join('\n');


  const program = mainTemplate.replace('[builded-snippets]', buildedSnippets);
  fs.writeFileSync(`${buildDir}/SnippetTest.${ext}`, program);
}

async function main() {
  const templatesPath = glob.sync(snippetTemplatesPath);
  const templates = {}
  for (const path of templatesPath) {
    templates[Path.basename(path)] = fs.readFileSync(path, 'utf8');
  }

  bundleAndBuildTests(glob.sync(javaSnippetPath), 'java', templates);
  bundleAndBuildTests(glob.sync(kotlinSnippetPath), 'kt', templates);
}

main();
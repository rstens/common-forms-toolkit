const config = require('config');
const fs = require('fs');
const path = require('path');
const router = require('express').Router();
const yaml = require('js-yaml');

const form = require('../forms/form');
const minesOperatorScreening = require('../forms/minesoperatorscreening');
const userManagement = require('../forms/usermanagement');

const getSpec = () => {
  const rawSpec = fs.readFileSync(path.join(__dirname, '../docs/v1.api-spec.yaml'), 'utf8');
  const spec = yaml.safeLoad(rawSpec);
  spec.components.securitySchemes.OpenID.openIdConnectUrl = `${config.get('server.keycloak.serverUrl')}/realms/${config.get('server.keycloak.realm')}/.well-known/openid-configuration`;
  return spec;
};

const formPath = form.mount(router);
const minesOperatorScreeningPath = minesOperatorScreening.mount(router);
const userManagementPath = userManagement.mount(router);

// Base v1 Responder
router.get('/', (_req, res) => {
  res.status(200).json({
    endpoints: [
      '/docs',
      formPath,
      minesOperatorScreeningPath,
      userManagementPath
    ]
  });
});

/** OpenAPI Docs */
router.get('/docs', (_req, res) => {
  const docs = require('../docs/docs');
  res.send(docs.getDocHTML('v1'));
});

/** OpenAPI YAML Spec */
router.get('/api-spec.yaml', (_req, res) => {
  res.status(200).type('application/yaml').send(yaml.safeDump(getSpec()));
});

/** OpenAPI JSON Spec */
router.get('/api-spec.json', (_req, res) => {
  res.status(200).json(getSpec());
});

module.exports = router;

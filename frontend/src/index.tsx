import React from 'react';
import ReactDOM from 'react-dom';
import Step1 from './Step1';
import Step2 from './Step2';
import RenderContent from './RenderContent'


ReactDOM.render(
  <Step1 />,
  document.getElementById('react-step1')
);

ReactDOM.render(
  <Step2 />,
  document.getElementById('react-step2')
);

ReactDOM.render(
  <RenderContent />,
  document.getElementById('render-graph')
);

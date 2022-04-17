import { Component } from 'react'
import Handlebars from "handlebars"
import './App.css'

var oldHref = "http://localhost:3000"

interface MyState {
  status: string,
  errMsg: string,
  template: HandlebarsTemplateDelegate<any>;
}

interface Props {
}

class RenderContent extends Component<Props, MyState> {

  constructor(props: Props) {
    super(props);
    this.state = {
      template: this.loadTemplate(),
      status: "",
      errMsg: ""
    };
  }

  async setContent (url: String) {
    const href = url + "";
    const response = await fetch(href);
    const json = await response.json();

    this.setState({
      errMsg: json["errmsg"],
      status: json["status"],
    })
  }

  loadTemplate (): HandlebarsTemplateDelegate<any> {
    const src = document.getElementById("render-template");
    return Handlebars.compile(src?.innerHTML, {});
  }
  
  async switch() {
    if (
      (window.location.href.split("?")[0] === "http://localhost:3000/render" 
      || window.location.href.split("?")[0] === "http://localhost:3000/reset") &&
      oldHref !== window.location.href
    ) {
      this.setContent(window.location.href);
      oldHref = window.location.href;
    }
  };
  

  render() {
    this.switch()
    return (
      <div className="RenderContent">
        <div
          dangerouslySetInnerHTML={{
            __html: this.state.template({ 
              status: this.state.status,
              errmsg: this.state.errMsg
            }),
          }}
        />
      </div>
    )
  };
};

export default RenderContent;

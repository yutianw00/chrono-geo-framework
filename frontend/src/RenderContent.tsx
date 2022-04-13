// import Handlebars from "handlebars"
import { Component } from 'react'
import './App.css'

var oldHref = "http://localhost:3000"




interface MyState {
  content: string
}

interface Props {
}

class RenderContent extends Component<Props, MyState> {

  constructor(props: Props) {
    super(props);
    this.state = {
      content: "whatever"
    };
  }

  async setContent (url: String) {
    const href = url + "";
    const response = await fetch(href);
    const json = await response.json();
    this.setState({
      content: json["renderhtml"]
    })
  }

  
  
  /*
  async initRender(url: String){
    const href = url + "";
    const response = await fetch(href);
    const json = await response.json();

    const newCells: Array<Cell> = this.convertToCell(json);
    this.setState({ cells: newCells })
  }
  */

  
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
            __html: this.state.content,
          }}
        />
      </div>
    )
  };
};

export default RenderContent;

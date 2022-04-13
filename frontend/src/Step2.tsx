import Handlebars from "handlebars"
import { Component } from 'react'
import './App.css'

var oldHref = "http://localhost:3000"

interface Cell {
  text: String;
  link: String;
  description: String;
  chosen: boolean;
}


interface MyState {
  cells: Array<Cell>;
  template: HandlebarsTemplateDelegate<any>;
}

interface Props {
}

class Step2 extends Component<Props, MyState> {

  constructor(props: Props) {
    super(props);
    this.state = {
      template: this.loadTemplate(),
      cells: [
        { text: "dummy Visual", description: "VISUAL dummy plugin for testing", link: "visualplugin?id=0", chosen: false },
        { text: "dummy Visual", description: "VISUAL dummy plugin for testing", link: "visualplugin?id=1", chosen: false },
      ],
    };
  }

  loadTemplate (): HandlebarsTemplateDelegate<any> {
    const src = document.getElementById("react-step2-template");
    return Handlebars.compile(src?.innerHTML, {});
  }

  convertToCell(p: any): Array<Cell> {
    const newCells: Array<Cell> = [];
    for (var i = 0; i < p["visualcells"].length; i++) {
      var c: Cell = {
        text: p["visualcells"][i]["name"],
        description: p["visualcells"][i]["description"],
        link: p["visualcells"][i]["link"],
        chosen: p["visualcells"][i]["chosen"]
      };
      newCells.push(c);
    }

    return newCells;
  }

  async chooseVisualPlugin(url: String){
    const href = "visualplugin?"+url.split("?")[1];
    const response = await fetch(href);
    const json = await response.json();

    const newCells: Array<Cell> = this.convertToCell(json);
    this.setState({ cells: newCells })
  }

  async initRender(url: String){
    const href = url + "";
    const response = await fetch(href);
    const json = await response.json();

    const newCells: Array<Cell> = this.convertToCell(json);
    this.setState({ cells: newCells })
  }
  

  async switch() {
    if (
      window.location.href.split("?")[0] === "http://localhost:3000/visualplugin" &&
      oldHref !== window.location.href
    ) {
      this.chooseVisualPlugin(window.location.href);
      oldHref = window.location.href;
    } else if (
      oldHref !== window.location.href // if request is called by the other React block
    ) {
      this.initRender("http://localhost:3000/init");
      oldHref = window.location.href;
    }
  };

  render() {
    this.switch()
    return (
      <div className="Step2">
        <div
          dangerouslySetInnerHTML={{
            __html: this.state.template({ 
              cells: this.state.cells, 
              numColStyle: Array(this.state.cells.length).fill("auto").join(" "),
            }),
          }}
        />
      </div>
    )
  };
};

export default Step2;

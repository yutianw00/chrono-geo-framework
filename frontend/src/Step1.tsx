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

class Step1 extends Component<Props, MyState> {

  constructor(props: Props) {
    super(props);
    this.state = {
      template: this.loadTemplate(),
      cells: [
        { text: "dummy Data", description: "DATA dummy plugin for testing", link: "dataplugin?id=0", chosen: false },
        { text: "dummy Data", description: "DATA dummy plugin for testing", link: "dataplugin?id=1", chosen: false },
      ],
    };
  }

  loadTemplate (): HandlebarsTemplateDelegate<any> {
    const src = document.getElementById("react-step1-template");
    return Handlebars.compile(src?.innerHTML, {});
  }

  convertToCell(p: any): Array<Cell> {
    const newCells: Array<Cell> = [];
    for (var i = 0; i < p["datacells"].length; i++) {
      var c: Cell = {
        text: p["datacells"][i]["name"],
        description: p["datacells"][i]["description"],
        link: p["datacells"][i]["link"],
        chosen: p["datacells"][i]["chosen"]
      };
      newCells.push(c);
    }

    return newCells;
  }

  async chooseDataPlugin(url: String){
    const href = "dataplugin?"+url.split("?")[1];
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

  async reset(url: String){
    const href = url + "";
    const response = await fetch(href);
    const json = await response.json();

    const newCells: Array<Cell> = this.convertToCell(json);
    this.setState({ cells: newCells })
  }



  async switch() {
    if (
      window.location.href.split("?")[0] === "http://localhost:3000/dataplugin" &&
      oldHref !== window.location.href
    ) {
      this.chooseDataPlugin(window.location.href);
      oldHref = window.location.href;
    } else if (
      window.location.href.split("?")[0] === "http://localhost:3000/reset" &&
      oldHref !== window.location.href 
    ) {
      this.reset(window.location.href);
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
      <div className="Step1">
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

export default Step1;

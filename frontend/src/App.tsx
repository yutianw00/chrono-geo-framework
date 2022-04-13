import Handlebars from "handlebars"
import { Component } from 'react'
import './App.css'

var oldHref = "http://localhost:3000"

interface Cell {
  text: String;
  clazz: String;
  link: String;
}


interface Plugin {
    name: String;
    link: String;
}

interface GameState {
  name: String;
  footer: String;
  cells: Array<Cell>;
  plugins: Array<Plugin>;
  numColStyle: String;
  currentPlayer: String;
  gameOverMsg: String;
  template: HandlebarsTemplateDelegate<any>;
}

interface Props {
}

class App extends Component<Props, GameState> {

  constructor(props: Props) {
    super(props);
    this.state = {
      template: this.loadTemplate(),
      cells: [
        { text: "", clazz: "", link: "/play?x=0&y=0" },
      ],
      name : "A Game Framework",
      footer : "No game is running",
      plugins : [
      { name: "Load Games", link:"/start"},
      ],
      numColStyle : "auto",
      currentPlayer : "",
      gameOverMsg : "",
    };
  }

  loadTemplate (): HandlebarsTemplateDelegate<any> {
    const src = document.getElementById("handlebars");
    return Handlebars.compile(src?.innerHTML, {});
  }

  convertToCell(p: any): Array<Cell> {
    const newCells: Array<Cell> = [];
    for (var i = 0; i < p["cells"].length; i++) {
      var c: Cell = {
        text: p["cells"][i]["text"],
        clazz: p["cells"][i]["clazz"],
        link: p["cells"][i]["link"],
      };
      newCells.push(c);
    }

    return newCells;
  }

  convertToPlugin(p: any): Array<Plugin> {
    const newPlugins: Array<Plugin> = [];
    for (var i = 0; i < p["plugins"].length; i++) {
          var plug: Plugin = {
            name: p["plugins"][i]["name"],
            link: p["plugins"][i]["link"],
          };
          newPlugins.push(plug);
        }

        return newPlugins;
  }


  async start(){
    const href = "start";
    const response = await fetch(href);

    const json = await response.json();
    const newPlugins: Array<Plugin> = this.convertToPlugin(json);
    this.setState({ plugins: newPlugins,})
  }

  async play(url: String) {
    const href = "play?" + url.split("?")[1];
    const response = await fetch(href);
    const json = await response.json();

    const newCells: Array<Cell> = this.convertToCell(json);
    const newPlugins: Array<Plugin> = this.convertToPlugin(json);
    this.setState({ cells: newCells, plugins: newPlugins, name: json["name"],footer:json["footer"], currentPlayer : json["currentPlayer"],
                                                                                numColStyle : json["numColStyle"],
                                                                                gameOverMsg : json["gameOverMsg"] })
  }

  async choosePlugin(url: String){
    const href = "plugin?"+url.split("?")[1];
    const response = await fetch(href);
    const json = await response.json();

    const newCells: Array<Cell> = this.convertToCell(json);
    const newPlugins: Array<Plugin> = this.convertToPlugin(json);
    this.setState({ cells: newCells, plugins: newPlugins, name: json["name"],footer:json["footer"],numColStyle : json["numColStyle"],
                                                                                currentPlayer : json["currentPlayer"],
                                                                                gameOverMsg : json["gameOverMsg"] })
  }


  async switch() {
    if (
      window.location.href.split("?")[0] === "http://localhost:3000/plugin" &&
      oldHref !== window.location.href
    ) {
      this.choosePlugin(window.location.href);
      oldHref = window.location.href;
    } else if (
      window.location.href.split("?")[0] === "http://localhost:3000/play" &&
      oldHref !== window.location.href
    ) {
      this.play(window.location.href);
      oldHref = window.location.href;
    } else if (
      window.location.href === "http://localhost:3000/" ||
      window.location.href === "http://localhost:3000/start"
    ){
      this.start();
      oldHref = window.location.href;
    }
  };

  render() {
    this.switch()
    return (
      <div className="App">
        <div
          dangerouslySetInnerHTML={{
            __html: this.state.template({ cells: this.state.cells, name: this.state.name, footer: this.state.footer,
             plugins: this.state.plugins, numColStyle: this.state.numColStyle, gameOverMsg: this.state.gameOverMsg,
              currentPlayer : this.state.currentPlayer}),
          }}
        />
      </div>
    )
  };
};

export default App;

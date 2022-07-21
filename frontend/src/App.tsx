import './App.css';
import AddCategory from './modules/component/category/AddCategory';
import GetCategories from './modules/component/category/GetCategories';
import Signup from './modules/component/authentication/Signup';
import Login from './modules/component/authentication/Login';

function App() {
  return (
    <div className="App">
      {/* <AddCategory />
      <GetCategories /> */}
      <Signup />
      <Login />
    </div>
  );
}

export default App;

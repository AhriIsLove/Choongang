import logo from './logo.svg';
import './App.css';
import { RouterProvider } from 'react-router-dom';//주의
import root from './router/root';//주의

function App() {
  return (
    //앱을 랜더링
    <RouterProvider router={root}></RouterProvider>
  );
};

export default App;

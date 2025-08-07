import { RouterProvider } from 'react-router-dom';
import root from "./router/root"

function App() {
  return (
    // 앱을 렌더링  
    <RouterProvider router={root}></RouterProvider>
  );
}

export default App;

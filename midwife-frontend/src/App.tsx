import './App.css';
import { Route, Routes } from 'react-router-dom';
import StandardAppBar from './components/StandardAppBar.tsx';
import { Home } from '@mui/icons-material';
import MidwifeComponent from './pages/MidwifeComponent.tsx';
import AreaComponent from './pages/AreaComponent.tsx';
import Brokerage from './pages/Brokerage.tsx';
import Women from './pages/Women.tsx';
import Service from './pages/Service.tsx';
import EditArea from './components/area/EditArea.tsx';

function App() {
  return (
    <>
      <StandardAppBar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/midwife" element={<MidwifeComponent />} />
        <Route path="/area" element={<AreaComponent />} />
        <Route path="/brokerage" element={<Brokerage />} />
        <Route path="/women" element={<Women />} />
        <Route path="/service" element={<Service />} />
        <Route path="/area/:postcode/:city/:district" element={<EditArea />} />
      </Routes>
    </>
  );
}

export default App;

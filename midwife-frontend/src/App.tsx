import './App.css';
import {Route, Routes} from "react-router-dom";
import StandardAppBar from "./components/StandardAppBar.tsx";
import {Home} from "@mui/icons-material";
import Midwife from "./pages/Midwife.tsx";
import Area from "./pages/Area.tsx";
import Brokerage from "./pages/Brokerage.tsx";
import Women from "./pages/Women.tsx";
import Service from "./pages/Service.tsx";

function App() {
  return(
      <>
        <StandardAppBar/>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/midwife" element={<Midwife />} />
            <Route path="/area" element={<Area />} />
            <Route path="/brokerage" element={<Brokerage />} />
            <Route path="/women" element={<Women />} />
            <Route path="/service" element={<Service />} />
        </Routes>
      </>
  )
}

export default App;


import './App.css';
import Navbar from './components/Navbar';
import AddVendor from './components/AddVendor'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import VendorList from './components/VendorList';
import UpdateVendor from './components/UpdateVendor';


function App() {
  return (
    <>
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route index element={<VendorList />}></Route>
        <Route path="/" element={<VendorList />}></Route>
        <Route path="/vendorList" element={<VendorList />}></Route>
        <Route path="/addVendor" element={<AddVendor />}></Route>
        <Route path="/editVendor/:id" element={<UpdateVendor />}/>
        <Route path="/addMenu/:id" element={<UpdateVendor />} />
      </Routes>
    </BrowserRouter>

    </>
  );
}

export default App;

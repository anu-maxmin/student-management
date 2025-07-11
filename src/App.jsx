import React from 'react'
import RegisterComponent from './components/RegisterComponent'
import { BrowserRouter, Route, Routes } from "react-router-dom";
import DisplayComponent from './components/DisplayComponent';
import DeleteComponent from './components/DeleteComponent';
import UpdateComponent from './components/UpdateComponent';

const App = () => {
  return (
    <div>
      <BrowserRouter>
      <Routes>
        <Route path='/' element={<DisplayComponent/>}></Route>
        <Route path='/add' element={<RegisterComponent/>}></Route>
        <Route path='/delete' element={<DeleteComponent/>}></Route>
        <Route path='/update' element={<UpdateComponent/>}></Route>
      </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App
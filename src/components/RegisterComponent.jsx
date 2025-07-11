import React, { useState } from 'react'
import axios, {  } from "axios";
import { useNavigate } from 'react-router-dom';

const RegisterComponent = () => {
    const [formData, setFormData] = useState({
        studentName: "",
        studentEmail: "",
        studentDepartment: "",
        studentPhone: "",
        studentDOB: ""
    });
    const [errors, setErrors] = useState({});
    const navigate = useNavigate("")

    const handleChange =(e) =>{
        const { name, value} = e.target;
        setFormData((preveData) =>({...preveData, [name] : value}))
    }

    const validateForm = ()=>{
        const newErrors = {}
        if(!formData.studentName || formData.studentName.trim() === "") newErrors.studentName = "Name is required";
        if(!formData.studentEmail || formData.studentEmail.trim() === "") newErrors.studentEmail = "Email is required";
        if(!formData.studentPhone || formData.studentPhone.trim() === "") newErrors.studentPhone = "Mobile Number is required";
        if(!formData.studentDOB || formData.studentDOB.trim() === "") newErrors.studentDOB = "Date of Birth is required";
        if(!formData.studentDepartment || formData.studentDepartment.trim() === "") newErrors.studentDepartment = "Date of Birth is required";
        
        return newErrors;
    }
    
    const handleSubmit =async(e) =>{
        e.preventDefault();
        const validationErrors = validateForm();

        if(Object.keys(validationErrors).length > 0){
            console.log(errors)
            setErrors(validationErrors);
        }else{
            setErrors({})
        }

        console.log(formData);

        try {
             const response = await axios.post("http://localhost:8081/api/v1/student/add", formData)
             console.log(response);
             navigate("/")
        } catch (error) {
            console.log("Error submittng data", error.response);
        }
    }
  return (
    <div>
        <h1>Register New Student</h1>
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="studentName">Name :</label>
                <input type="text" name='studentName' id='studentName' value={formData.studentName} onChange={handleChange}/>
                {errors && <p style={{color : " red"}}>{errors.studentName}</p>}
            </div>
            <div>
                <label htmlFor="studentEmail">Email :</label>
                <input type="email" name='studentEmail' id='studentEmail' value={formData.studentEmail} onChange={handleChange}/>
                {errors && <p style={{color : " red"}}>{errors.studentEmail}</p>}
            </div>
            <div>
                <label htmlFor="studentPhone">Mobile Number :</label>
                <input type="text" name='studentPhone' id='studentPhone' value={formData.studentPhone} onChange={handleChange}/>
                {errors && <p style={{color : " red"}}>{errors.studentPhone}</p>}
            </div>
            <div>
                <label htmlFor="studentDOB">Date of Birth :</label>
                <input type="date" name='studentDOB' id='studentDOB' value={formData.studentDOB} onChange={handleChange}/>
                {errors && <p style={{color : " red"}}>{errors.studentDOB}</p>}
            </div>
            <div>
                <label htmlFor="studentAge">Age :</label>
                <input type="text" name='studentAge' id='studentAge' disabled/>
            </div>
            <div>
                <label htmlFor="studentDepartment">Department:</label>
                <select name="studentDepartment" id="studentDepartment" value={formData.studentDepartment} onChange={handleChange}>
                    <option value="" disabled>Select</option>
                    <option value="CSE">CSE</option>
                    <option value="ECE">ECE</option>
                    <option value="IT">IT</option>
                </select>  
                {errors && <p style={{color : " red"}}>{errors.studentDepartment}</p>}
            </div>
            <button type="submit">Register</button>
        </form>
    </div>
  )
}

export default RegisterComponent
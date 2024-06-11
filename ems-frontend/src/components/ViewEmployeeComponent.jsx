import { useEffect, useState } from 'react'
import { getEmployee } from '../services/EmployeeService';
import { useParams } from 'react-router-dom';

const ViewEmployeeComponent = () => {

    const {id} = useParams();
    // const [employee, setEmployee] = useState([]);
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');

    useEffect(() => {
        if(id){
            getEmployee(id).then((response) => {
                setFirstName(response.data.firstName);
                setLastName(response.data.lastName);
                setEmail(response.data.email);
            }).catch(error => {
                console.error(error);
            })
        }
    }, [id]);

  return (
    <div>
      <h2 className='text-center'>Detail of Employee</h2>
      <div className='card-body'>
        <div className='text-center'>First Name: {firstName}</div>
        <div className='text-center'>Last Name: {lastName}</div>
        <div className='text-center'>Email: {email}</div>
      </div>
    </div>
  )
}

export default ViewEmployeeComponent

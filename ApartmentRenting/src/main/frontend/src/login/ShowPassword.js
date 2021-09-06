import React from 'react';
import './ShowPassword.css'

const ShowPassword = ({showPassword, setShowPassword}) => {
  return (
    <table className="mt-2">
    <tr>
        <td><input
            name="showPassword"
            type="checkbox"
            checked={showPassword}
            onChange={() => setShowPassword(!showPassword)} />
         </td>
        <td><p className="mt-2 ml-2"> Show password </p> </td>
    </tr>
    </table>
  )
 }
export default ShowPassword
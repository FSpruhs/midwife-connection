import React, { useState } from 'react';

const MidwifeForm: React.FC = () =>  {
  const [firstName, setFirstName] = useState<string>('');
  const [lastName, setLastName] = useState<string>('');

  const handleInputFirstName = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFirstName(e.target.value)
  }

  const handleInputLastName = (e: React.ChangeEvent<HTMLInputElement>) => {
    setLastName(e.target.value)
  }

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    console.log(firstName);
    console.log(lastName);
  }

  return(
    <>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Vorname</label>
          <input
            type='text'
            value={firstName}
            onChange={handleInputFirstName}
          />
        </div>
        <div>
          <label>Nachname</label>
          <input
            type='text'
            value={lastName}
            onChange={handleInputLastName}
          />
        </div>
        <button type='submit'>Anlegen</button>
      </form>
    </>
  )
}

export default MidwifeForm;
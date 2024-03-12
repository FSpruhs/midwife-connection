import React, { useState } from 'react';
import { gql, useMutation } from '@apollo/client';
import {Button, TextField } from "@mui/material";



const MidwifeForm: React.FC = () => {
  const CREATE_MIDWIFE = gql`
      mutation CreateMidwife($firstName: String!, $lastName: String!) {
          createMidwife(firstName: $firstName, lastName: $lastName) {
              id,
              firstName,
              lastName
          }
      }
  `;

  const [firstName, setFirstName] = useState<string>('');
  const [lastName, setLastName] = useState<string>('');
  const [createMidwife] = useMutation(CREATE_MIDWIFE);

  const handleInputFirstName = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFirstName(e.target.value);
  };

  const handleInputLastName = (e: React.ChangeEvent<HTMLInputElement>) => {
    setLastName(e.target.value);
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    createMidwife({ variables: { firstName: firstName, lastName: lastName } })
  };

  return (
    <>
      <form
        onSubmit={handleSubmit}
      >
          <TextField label="Vorname" value={firstName} onChange={handleInputFirstName} />
          <TextField label="Nachname" type="text" value={lastName} onChange={handleInputLastName} />
          <Button type="submit">Anlegen</Button>
      </form>
    </>
  );
};

export default MidwifeForm;

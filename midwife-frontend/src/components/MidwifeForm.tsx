import React, { useState } from 'react';
import { useMutation } from '@apollo/client';
import { Box, Button, TextField } from '@mui/material';
import { CREATE_MIDWIFE } from '../queries/midwife';

const MidwifeForm: React.FC = () => {
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
    createMidwife({ variables: { firstName: firstName, lastName: lastName } });
  };

  return (
    <Box
      component="form"
      sx={{
        '& .MuiTextField-root': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
      onSubmit={handleSubmit}
    >
      <TextField label="Vorname" value={firstName} onChange={handleInputFirstName} />
      <TextField label="Nachname" type="text" value={lastName} onChange={handleInputLastName} />
      <Button type="submit">Anlegen</Button>
    </Box>
  );
};

export default MidwifeForm;

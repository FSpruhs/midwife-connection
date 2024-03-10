import React, { useState } from 'react';
import { Button, Label, TextInput } from 'flowbite-react';
import { gql, useMutation } from '@apollo/client';



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
        className="flex max-w-md flex-col gap-4"
        onSubmit={handleSubmit}
      >
        <div>
          <Label>Vorname</Label>
          <TextInput type="text" value={firstName} onChange={handleInputFirstName} />
        </div>
        <div>
          <Label>Nachname</Label>
          <TextInput type="text" value={lastName} onChange={handleInputLastName} />
        </div>
        <Button type="submit">Anlegen</Button>
      </form>
    </>
  );
};

export default MidwifeForm;

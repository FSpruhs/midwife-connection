import { gql } from '@apollo/client';

export const CREATE_MIDWIFE = gql`
  mutation CreateMidwife($firstName: String!, $lastName: String!) {
    createMidwife(firstName: $firstName, lastName: $lastName) {
      id
      firstName
      lastName
    }
  }
`;

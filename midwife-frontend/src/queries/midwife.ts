import { gql } from '@apollo/client';

export const CREATE_MIDWIFE = gql`
  mutation CreateMidwife($firstName: String!, $lastName: String!, $areas: [Int!]!) {
    createMidwife(firstName: $firstName, lastName: $lastName, areas: $areas) {
      id
      firstName
      lastName
      areas
    }
  }
`;

export const DELETE_MIDWIFE = gql`
    mutation DeleteMidwife($id: ID!) {
        deleteMidwife(id: $id)
    }
`;

export const GET_MIDWIFES = gql`
  query GetMidwifes {
    getMidwifes {
      id
      firstName
      lastName
      areas
    }
  }
`;

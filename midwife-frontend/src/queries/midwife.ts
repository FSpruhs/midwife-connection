import { gql } from '@apollo/client';

export const CREATE_MIDWIFE = gql`
  mutation CreateMidwife(
      $firstName: String!,
      $lastName: String!,
      $areas: [Int!]!
  ) {
    createMidwife(firstName: $firstName, lastName: $lastName, areas: $areas) {
      id
      firstName
      lastName
      areas
    }
  }
`;

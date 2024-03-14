import { gql } from '@apollo/client';

export const CREATE_AREA = gql`
  mutation CreateArea($city: String!, $district: String!, $postcode: Int!) {
    createArea(city: $city, district: $district, postcode: $postcode) {
      postcode
      city
      district
    }
  }
`;

export const UPDATE_AREA = gql`
  mutation UpdateArea($city: String!, $district: String!, $postcode: Int!) {
    updateArea(city: $city, district: $district, postcode: $postcode) {
      postcode
      city
      district
    }
  }
`;

export const DELETE_AREA = gql`
  mutation DeleteArea($postcode: Int!) {
    deleteArea(postcode: $postcode)
  }
`;

export const GET_AREAS = gql`
  query GetAreas {
    getAreas {
      postcode
      city
      district
    }
  }
`;

export interface Midwife {
  id: string;
  firstName: string;
  lastName: string;
  areas: number[];
}

export interface MidwifeListGraphQlData {
  getMidwifes: Midwife[];
}

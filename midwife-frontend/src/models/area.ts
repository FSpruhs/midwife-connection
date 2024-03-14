export interface AreaListGraphQlData {
  getAreas: Area[];
}

export interface Area {
  postcode: number;
  city: string;
  district: string;
}

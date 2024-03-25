import MidwifeForm from './MidwifeForm.tsx';
import { useQuery } from '@apollo/client';
import { AreaListGraphQlData } from '../../models/area.ts';
import { GET_AREAS } from '../../queries/area.ts';
import { Midwife } from '../../models/midwife.ts';

export default function MidwifeEdit() {

  const { data: areaData } = useQuery<AreaListGraphQlData>(GET_AREAS);

  const handleSubmit = (midwife: Midwife) => {
    console.log(midwife);
  }

  return (
      <MidwifeForm areas={areaData} invokeSubmit={handleSubmit}/>
  );
}
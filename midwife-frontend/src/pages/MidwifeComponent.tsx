import MidwifeForm from '../components/midwife/MidwifeForm.tsx';
import { useMutation, useQuery } from '@apollo/client';
import { CREATE_MIDWIFE, GET_MIDWIFES } from '../queries/midwife.ts';
import { AreaListGraphQlData } from '../models/area.ts';
import { GET_AREAS } from '../queries/area.ts';
import MidwifeList from '../components/midwife/MidwifeList.tsx';
import { Midwife, MidwifeListGraphQlData } from '../models/midwife.ts';

export default function MidwifeComponent() {
  const { data: areaData } = useQuery<AreaListGraphQlData>(GET_AREAS);
  const { data: midwifeData, refetch } = useQuery<MidwifeListGraphQlData>(GET_MIDWIFES);
  const [createMidwife] = useMutation(CREATE_MIDWIFE);

  const handleSubmit = (data: Midwife) => {
    createMidwife({
      variables: {
        firstName: data.firstName,
        lastName: data.lastName,
        areas: data.areas,
      },
    }).then(() => refetch());
  };

  return (
    <>
      <MidwifeList midwifeData={midwifeData?.getMidwifes} />
      <MidwifeForm invokeSubmit={handleSubmit} areas={areaData} />
    </>
  );
}

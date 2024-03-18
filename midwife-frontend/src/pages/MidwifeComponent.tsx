import MidwifeForm from '../components/midwife/MidwifeForm.tsx';
import { useMutation, useQuery } from '@apollo/client';
import { CREATE_MIDWIFE } from '../queries/midwife.ts';
import { AreaListGraphQlData } from '../models/area.ts';
import { GET_AREAS } from '../queries/area.ts';

export default function MidwifeComponent() {
  const { data } = useQuery<AreaListGraphQlData>(GET_AREAS);
  const [createMidwife] = useMutation(CREATE_MIDWIFE);

  const handleSubmit = (data: Midwife) => {
    createMidwife({
      variables: {
        firstName: data.firstName,
        lastName: data.lastName,
        areas: data.areas,
      },
    }).then((response) => console.log(response));
  };

  return <MidwifeForm invokeSubmit={handleSubmit} areas={data} />;
}

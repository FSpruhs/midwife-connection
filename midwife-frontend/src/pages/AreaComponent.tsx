import AreaForm from '../components/area/AreaForm.tsx';
import AreaList from '../components/area/AreaList.tsx';
import { useMutation, useQuery } from '@apollo/client';
import { Area, AreaListGraphQlData } from '../models/area.ts';
import { CREATE_AREA, DELETE_AREA, GET_AREAS } from '../queries/area.ts';
import { useNavigate } from 'react-router-dom';

export default function AreaComponent() {
  const { data, refetch } = useQuery<AreaListGraphQlData>(GET_AREAS);

  const [deleteArea] = useMutation(DELETE_AREA);
  const [createArea] = useMutation(CREATE_AREA);
  const navigate = useNavigate();

  const handleDelete = (area: Area) => {
    deleteArea({
      variables: {
        postcode: area.postcode,
      },
    }).then(() => refetch());
  };

  const handleEdit = (area: Area) => {
    navigate(`/area/${area.postcode}/${area.city}/${area.district}`);
  };

  const handleSubmit = (data: Area) => {
    createArea({
      variables: {
        postcode: data.postcode,
        district: data.district,
        city: data.city,
      },
    }).then(() => refetch());
  };

  return (
    <>
      <AreaList areaData={data?.getAreas} handleDelete={handleDelete} handleEdit={handleEdit} />
      <AreaForm invokeSubmit={handleSubmit} />
    </>
  );
}

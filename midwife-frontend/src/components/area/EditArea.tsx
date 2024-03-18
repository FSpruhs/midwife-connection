import { useParams, useNavigate } from 'react-router-dom';
import { useMutation } from '@apollo/client';
import { GET_AREAS, UPDATE_AREA } from '../../queries/area.ts';
import { Area } from '../../models/area.ts';
import AreaForm from './AreaForm.tsx';

export default function EditArea() {
  const routeParams = useParams();
  const [updateArea] = useMutation(UPDATE_AREA, {
    refetchQueries: [{ query: GET_AREAS }],
  });
  const navigate = useNavigate();

  const onSubmit = (data: Area) => {
    updateArea({
      variables: {
        postcode: data.postcode,
        district: data.district,
        city: data.city,
      },
    }).then((data) => console.log(data));
    navigate('/area');
  };

  return !routeParams.city || !routeParams.district || !routeParams.postcode ? (
    <div>Invalid route parameters</div>
  ) : (
    <AreaForm
      invokeSubmit={onSubmit}
      defaultArea={{
        city: routeParams.city,
        district: routeParams.district,
        postcode: parseInt(routeParams.postcode),
      }}
      submitButtonText={'Ändern'}
      postcodeDisabled={true}
    />
  );
}

import { useParams, useNavigate } from 'react-router-dom';
import { useMutation } from '@apollo/client';
import { GET_AREAS, UPDATE_AREA } from '../../queries/area.ts';
import { Button, Stack, TextField } from '@mui/material';
import { useForm } from 'react-hook-form';

type Inputs = {
  city: string;
  district: string;
  postcode: string;
};

export default function EditArea() {
  const routeParams = useParams();
  const [updateArea] = useMutation(UPDATE_AREA, {
    refetchQueries: [{ query: GET_AREAS }],
  });
  const navigate = useNavigate();

  const {
    reset,
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<Inputs>({
    defaultValues: {
      city: routeParams.city,
      district: routeParams.district,
      postcode: routeParams.postcode,
    },
  });

  const onSubmit = (data: Inputs) => {
    updateArea({
      variables: {
        postcode: data.postcode,
        district: data.district,
        city: data.city,
      },
    }).then((data) => console.log(data));
    reset();
    navigate('/area');
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <Stack spacing={2} direction={'row'} marginTop={4}>
        <TextField
          {...register('district', { required: true })}
          label={'Viertel'}
          variant={'outlined'}
          type={'text'}
          margin={'normal'}
          error={!!errors.district}
          helperText={errors.district ? 'Viertel ist erforderlich' : ''}
        />
        <TextField
          {...register('city', { required: true })}
          label={'Stadt'}
          variant={'outlined'}
          type={'text'}
          margin={'normal'}
          error={!!errors.city}
          helperText={errors.city ? 'Stadt ist erforderlich' : ''}
        />
        <TextField
          {...register('postcode', { min: 10000, max: 99999, required: true })}
          label={'Postleitzahl'}
          variant={'outlined'}
          type={'text'}
          margin={'normal'}
          error={!!errors.postcode}
          helperText={errors.postcode ? 'Fünfstellige Postleitzahl erforderlich' : ''}
          disabled
        />
      </Stack>
      <Button type="submit">Bearbeiten</Button>
    </form>
  );
}

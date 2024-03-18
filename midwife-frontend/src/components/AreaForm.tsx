import { Button, Stack, TextField } from '@mui/material';
import { useForm } from 'react-hook-form';
import { Area } from '../models/area.ts';

interface Props {
  invokeSubmit: (data: Area) => void;
}

export default function AreaForm(props: Readonly<Props>) {
  const {
    reset,
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<Area>();

  const onSubmit = (data: Area) => {
    props.invokeSubmit(data);
    reset();
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
          type={'number'}
          margin={'normal'}
          error={!!errors.postcode}
          helperText={errors.postcode ? 'Fünfstellige Postleitzahl erforderlich' : ''}
        />
      </Stack>
      <Button type="submit">Anlegen</Button>
    </form>
  );
}

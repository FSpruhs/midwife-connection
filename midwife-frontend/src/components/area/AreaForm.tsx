import { Button, Stack, TextField } from '@mui/material';
import { useForm } from 'react-hook-form';
import { Area } from '../../models/area.ts';

interface Props {
  invokeSubmit: (data: Area) => void;
  postcodeDisabled?: boolean;
  defaultArea?: Area;
  submitButtonText?: string;
}

export default function AreaForm({
  invokeSubmit,
  postcodeDisabled = false,
  submitButtonText = 'Anlegen',
  defaultArea = {
    city: '',
    district: '',
    postcode: 0,
  },
}: Readonly<Props>) {
  const {
    reset,
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<Area>({
    defaultValues: {
      city: defaultArea.city,
      district: defaultArea.district,
      postcode: defaultArea.postcode,
    },
  });

  const onSubmit = (data: Area) => {
    invokeSubmit(data);
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
          disabled={postcodeDisabled}
        />
      </Stack>
      <Button type="submit">{submitButtonText}</Button>
    </form>
  );
}

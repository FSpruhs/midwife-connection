import { Button, Checkbox, FormControlLabel, Stack, TextField } from '@mui/material';
import { useForm } from 'react-hook-form';
import { AreaListGraphQlData } from '../../models/area.ts';
import { useEffect, useState } from 'react';
import { Midwife } from '../../models/midwife.ts';

interface Props {
  invokeSubmit: (data: Midwife) => void;
  areas: AreaListGraphQlData | undefined;
}

export default function MidwifeForm(props: Readonly<Props>) {
  const [postCodeBools, setPostCodeBools] = useState<boolean[]>([]);
  useEffect(() => {
    if (props.areas) {
      setPostCodeBools(props.areas.getAreas.map(() => false));
    }
  }, [props.areas]);

  const {
    reset,
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<Midwife>({ defaultValues: { areas: [] } });

  const onSubmit = (data: Midwife) => {
    props.areas?.getAreas.forEach((area, index) => {
      if (postCodeBools[index]) {
        data.areas.push(area.postcode);
      }
    });
    props.invokeSubmit(data);
    reset();
    setPostCodeBools(props.areas?.getAreas.map(() => false) ?? []);
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <Stack spacing={2} direction={'row'} marginTop={4}>
        <TextField
          {...register('firstName', { required: true })}
          label={'Vorname'}
          variant={'outlined'}
          type={'text'}
          margin={'normal'}
          error={!!errors.firstName}
          helperText={errors.firstName ? 'Vorname ist erforderlich' : ''}
        />
        <TextField
          {...register('lastName', { required: true })}
          label={'Vorname'}
          variant={'outlined'}
          type={'text'}
          margin={'normal'}
          error={!!errors.lastName}
          helperText={errors.lastName ? 'Nachname ist erforderlich' : ''}
        />
      </Stack>

      {props.areas && (
        <Stack marginTop={2}>
          {props.areas.getAreas.map((area, index) => (
            <FormControlLabel
              key={area.postcode}
              control={
                <Checkbox
                  checked={postCodeBools.length > 0 ? postCodeBools[index] : false}
                  onChange={(e) => {
                    setPostCodeBools([
                      ...postCodeBools.slice(0, index),
                      e.target.checked,
                      ...postCodeBools.slice(index + 1),
                    ]);
                  }}
                />
              }
              label={area.postcode + ' ' + area.district + ' ' + area.city}
            />
          ))}
        </Stack>
      )}
      <Button type="submit">Anlegen</Button>
    </form>
  );
}

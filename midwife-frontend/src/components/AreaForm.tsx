import {
  Button,
  Paper,
  Stack,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TextField,
} from '@mui/material';
import { useForm } from 'react-hook-form';
import { useMutation, useQuery } from '@apollo/client';
import IconButton from '@mui/material/IconButton';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import {CREATE_AREA, DELETE_AREA, GET_AREAS} from "../queries/area.ts";
import {Area, AreaListGraphQlData} from "../models/area.ts";
import { useNavigate } from 'react-router-dom';

type Inputs = {
  city: string;
  district: string;
  postcode: number;
};

export default function AreaForm() {

  const { data, refetch } = useQuery<AreaListGraphQlData>(GET_AREAS);
  const [createArea] = useMutation(CREATE_AREA);
  const [deleteArea] = useMutation(DELETE_AREA);
  const navigate = useNavigate();

  const {
    reset,
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<Inputs>();

  const handleDelete = (area: Area) => {
      deleteArea({
            variables: {
                postcode: area.postcode
            },
      }).then(() => refetch());
  };

  const handleEdit = (area: Area) => {
      navigate(`/area/${area.postcode}/${area.city}/${area.district}`);
  }

  const onSubmit = (data: Inputs) => {
    createArea({
      variables: {
        postcode: data.postcode,
        district: data.district,
        city: data.city,
      },
    }).then(() => refetch());
    reset();
  };

  return (
    <>
      <TableContainer component={Paper}>
        {data?.getAreas == undefined ? (
          <p>Loading...</p>
        ) : (
          <Table stickyHeader sx={{ minWidth: 650 }} aria-label="sticky table">
            <TableHead>
              <TableRow>
                <TableCell>
                  <h3>Postleitzahl</h3>
                </TableCell>
                <TableCell>
                  <h3>Viertel</h3>
                </TableCell>
                <TableCell>
                  <h3>Stadt</h3>
                </TableCell>
                <TableCell></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {data.getAreas.map((area) => (
                <TableRow key={area.postcode} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                  <TableCell align="left">{area.postcode}</TableCell>
                  <TableCell align="left">{area.district}</TableCell>
                  <TableCell align="left">{area.city}</TableCell>
                  <TableCell align="center">
                    <IconButton aria-label="edit" onClick={() => handleEdit(area)}>
                      <EditIcon />
                    </IconButton>
                    <IconButton aria-label="delete" onClick={() => handleDelete(area)}>
                      <DeleteIcon />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        )}
      </TableContainer>
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
    </>
  );
}

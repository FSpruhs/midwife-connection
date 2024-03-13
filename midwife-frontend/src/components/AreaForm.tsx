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
    TextField
} from "@mui/material";
import {useForm} from "react-hook-form";
import {gql, useMutation, useQuery} from "@apollo/client";
import IconButton from "@mui/material/IconButton";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';

interface AreaListData {
    getAreas: Area[]
}

interface Area {
    postcode: number,
    city: string,
    district: string,
}

type Inputs = {
    city: string
    district: string
    postcode: number
}

export default function AreaForm() {

    const CREATE_AREA = gql`
        mutation CreateArea($city: String!, $district: String!, $postcode: Int!) {
            createArea(city: $city, district: $district, postcode: $postcode) {
                postcode,
                city,
                district
            }
        }
    `;

    const GET_AREAS = gql`
        query GetAreas {
            getAreas {
                postcode,
                city,
                district
            }
        }
    `;




    const {data} = useQuery<AreaListData>(GET_AREAS);
    console.log(data)
    const areas = data?.getAreas || [];
    console.log(areas)
    console.log(data?.getAreas)
    const [createArea] = useMutation(CREATE_AREA);

    const {
        reset,
        register,
        handleSubmit,
        formState: {errors}
    } = useForm<Inputs>()

    const onSubmit = (data: Inputs) => {
        createArea({ variables: {
                postcode: data.postcode,
                district: data.district,
                city: data.city }
        })
        reset();
    }

    return (
        <>
            <TableContainer component={Paper}>
                {data?.getAreas == undefined ? (<p>Loading...</p>) :
                <Table stickyHeader sx={{ minWidth: 650 }} aria-label="sticky table">
                    <TableHead>
                        <TableRow>
                            <TableCell><h3>Postleitzahl</h3></TableCell>
                            <TableCell><h3>Viertel</h3></TableCell>
                            <TableCell><h3>Stadt</h3></TableCell>
                            <TableCell></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {data.getAreas.map((area) => (
                            <TableRow
                                key={area.postcode}
                                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                            >
                                <TableCell align="left">{area.postcode}</TableCell>
                                <TableCell align="left">{area.district}</TableCell>
                                <TableCell align="left">{area.city}</TableCell>
                                <TableCell align="center">
                                    <IconButton aria-label="delete">
                                        <EditIcon />
                                    </IconButton>
                                    <IconButton aria-label="delete">
                                        <DeleteIcon />
                                    </IconButton>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
                }
            </TableContainer>
        <form onSubmit={handleSubmit(onSubmit)}>
            <Stack spacing={2} direction={"row"} marginTop={4}>
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
                {...register('postcode', { min: 10000, max: 99999 , required: true })}
                label={'Postleitzahl'}
                variant={'outlined'}
                type={'number'}
                margin={'normal'}
                error={!!errors.postcode}
                helperText={errors.postcode ? 'Fünfstellige Postleitzahl erforderlich' : ''}
            />
            </Stack>
            <Button type='submit'>Submit</Button>
        </form>
        </>
    );
}
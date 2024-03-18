import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import IconButton from '@mui/material/IconButton';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { Area, AreaListGraphQlData } from '../models/area.ts';

interface Props {
  areaData: AreaListGraphQlData | undefined;
  handleDelete: (area: Area) => void;
  handleEdit: (area: Area) => void;
}

export default function AreaList(props: Readonly<Props>) {
  return (<TableContainer component={Paper}>
    {props.areaData?.getAreas == undefined ? (
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
          {props.areaData.getAreas.map((area) => (
            <TableRow key={area.postcode} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
              <TableCell align="left">{area.postcode}</TableCell>
              <TableCell align="left">{area.district}</TableCell>
              <TableCell align="left">{area.city}</TableCell>
              <TableCell align="center">
                <IconButton aria-label="edit" onClick={() => props.handleEdit(area)}>
                  <EditIcon />
                </IconButton>
                <IconButton aria-label="delete" onClick={() => props.handleDelete(area)}>
                  <DeleteIcon />
                </IconButton>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    )}
  </TableContainer>);
}
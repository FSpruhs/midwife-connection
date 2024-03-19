import { Midwife } from '../../models/midwife.ts';
import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import IconButton from '@mui/material/IconButton';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

interface Props {
  midwifeData: Midwife[] | undefined;
  handleDelete: (midwife: Midwife) => void;
  handleEdit: (midwife: Midwife) => void;
}

export default function MidwifeList(props: Readonly<Props>) {
  return (
    <TableContainer component={Paper}>
      {props.midwifeData == undefined ? (
        <p>Loading...</p>
      ) : (
        <Table stickyHeader sx={{ minWidth: 650 }} aria-label="sticky table">
          <TableHead>
            <TableRow>
              <TableCell>
                <h3>Vorname</h3>
              </TableCell>
              <TableCell>
                <h3>Nachname</h3>
              </TableCell>
              <TableCell>
                <h3>Gebiete</h3>
              </TableCell>
              <TableCell></TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {props.midwifeData.map((midwife) => (
              <TableRow key={midwife.id} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                <TableCell align="left">{midwife.firstName}</TableCell>
                <TableCell align="left">{midwife.lastName}</TableCell>
                <TableCell align="left">{midwife.areas}</TableCell>
                <TableCell align="center">
                  <IconButton aria-label="edit" onClick={() => props.handleEdit(midwife)}>
                    <EditIcon />
                  </IconButton>
                  <IconButton aria-label="delete" onClick={() => props.handleDelete(midwife)}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      )}
    </TableContainer>
  );
}

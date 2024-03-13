import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import { Menu, MenuItem} from "@mui/material";
import {useState} from "react";
import {Link} from "react-router-dom";

export default function StandardAppBar() {

    const dummyMenuItems = [
        {
            title: "Add Item"
        },
        {
            title: "Move Item"
        },
        {
            title: "Delete Item"
        }
    ];

    const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        onClick={handleClick}
                        sx={{ mr: 2 }}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Menu
                        id="simple-menu"
                        anchorEl={anchorEl}
                        keepMounted
                        open={Boolean(anchorEl)}
                        onClose={handleClose}
                    >
                        {dummyMenuItems.map(item => (
                            <MenuItem onClick={handleClose} key={item.title} value={item.title}>
                                {item.title}
                            </MenuItem>
                        ))}
                    </Menu>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        <Link style={{ textDecoration:"none", color:"white"}} to={"/"}>Home</Link>
                    </Typography>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        <Link style={{ textDecoration:"none", color:"white"}} to={"/midwife"}>Hebamme</Link>
                    </Typography>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        <Link style={{ textDecoration:"none", color:"white"}} to={"/area"}>Gebiet</Link>
                    </Typography>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        <Link style={{ textDecoration:"none", color:"white"}} to={"/service"}>Leistung</Link>
                    </Typography>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        <Link style={{ textDecoration:"none", color:"white"}} to={"/women"}>Frauen</Link>
                    </Typography>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        <Link style={{ textDecoration:"none", color:"white"}} to={"/brokerage"}>Vermittlung</Link>
                    </Typography>
                </Toolbar>
            </AppBar>
        </Box>
    );
}
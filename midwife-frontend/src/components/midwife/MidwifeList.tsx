import { Midwife } from '../../models/midwife.ts';

interface Props {
  midwifeData: Midwife[] | undefined;
}

export default function MidwifeList(props: Readonly<Props>) {
  return (
    <>
      {props.midwifeData == undefined ? (
        <p>loading</p>
      ) : (
        props.midwifeData.map((midwife) => (
          <div key={midwife.id}>
            <h3>
              {midwife.firstName} {midwife.lastName}
            </h3>
            <p>{midwife.areas}</p>
          </div>
        ))
      )}
    </>
  );
}

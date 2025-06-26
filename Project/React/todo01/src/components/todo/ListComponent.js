//
const initState = {
    dtoList: [],
    pageNumList: [],
    pageRequestDTO: null,
    prev:false,
    next:false,
    totalCount: 0,
    prevPage: 0,
    nextPage: 0,
    totalPage: 0,
    current: 0
};

const ListComponent = () => {
    // serverData는 나중에 사용
    // initState라는 초기값을 가진 serverData라는 상태 변수를 만들 거고,
    // 이 변수의 값을 업데이트할 때는 setServerData라는 함수를 사용
    // React 커뮤니티에서 약속처럼 쓰는 표준 컨벤션 --> setServerData
    const [serverData, setServerData] = useState(initState);
};
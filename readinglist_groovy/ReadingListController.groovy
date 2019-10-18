@Controller
@RequestMapping("/")
class ReadingListController {
    String reader = "Craig"

    @Autowired
    ReadingListRepository readingListRepository // 注入readingListRepository

    @RequestMapping(method=RequestMethod.GET)
    def readersBooks(Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader) // 获取阅读列表

        if (readingList) {
            model.addAttribute("books", readingList) // 设置模型
        }

        "readingList" // 返回视图名称
    }

    @RequestMapping(method=RequestMethod.POST)
    def addToReadingList(Book book) {
        book.setReader(reader)
        readingListRepository.save(book) // 保存图书
        "redirect:/" //POST后重定向
    }

}
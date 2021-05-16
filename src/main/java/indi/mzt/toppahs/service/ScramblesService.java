package indi.mzt.toppahs.service;

import indi.mzt.toppahs.entity.Scramble;
import indi.mzt.toppahs.main.annotations.Service;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.transcoder.*;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.util.SVGConstants;
import org.graalvm.compiler.serviceprovider.ServiceProvider;
import org.worldcubeassociation.tnoodle.puzzle.*;
import org.worldcubeassociation.tnoodle.scrambles.InvalidScrambleException;
import org.worldcubeassociation.tnoodle.scrambles.Puzzle;
import org.worldcubeassociation.tnoodle.svglite.Svg;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

class NNNCubePuzzle extends CubePuzzle{
    public NNNCubePuzzle(int size) {
        super(size);
    }
    public int getRandomMoveCount(){
        return size < 12?super.getRandomMoveCount():(20 * size - 40);
    }
}

@Service
public enum ScramblesService {
    TWO(new TwoByTwoCubePuzzle()),
    THREE(new ThreeByThreeCubePuzzle()),
    FOUR(new FourByFourCubePuzzle()),
    FIVE(new NNNCubePuzzle(5)),
    SIX(new NNNCubePuzzle(6)),
    SEVEN(new NNNCubePuzzle(7)),
    EIGHT(new NNNCubePuzzle(8)),
    NINE(new NNNCubePuzzle(9)),
    TEN(new NNNCubePuzzle(10)),
    ELEVEN(new NNNCubePuzzle(11)),
    TWELVE(new NNNCubePuzzle(12)),
    THIRTEEN(new NNNCubePuzzle(13)),
    FOURTEEN(new NNNCubePuzzle(14)),
    FIFTEEN(new NNNCubePuzzle(15)),
    SIXTEEN(new NNNCubePuzzle(16)),
    SEVENTEEN(new NNNCubePuzzle(17)),
    FOUR_FAST(new FourByFourRandomTurnsCubePuzzle()),
    THREE_NI(new NoInspectionThreeByThreeCubePuzzle()),
    FOUR_NI(new NoInspectionFourByFourCubePuzzle()),
    FIVE_NI(new NoInspectionFiveByFiveCubePuzzle()),
    THREE_FM(new ThreeByThreeCubeFewestMovesPuzzle()),
    PYRA(new PyraminxPuzzle()),
    SQ1(new SquareOnePuzzle()),
    MEGA(new MegaminxPuzzle()),
    CLOCK(new ClockPuzzle()),
    SKEWB(new SkewbPuzzle());

    //private Scramble scramble=new Scramble();
    private Puzzle puzzle;
    private String scramble;
    private File puzzleImage;
    ScramblesService(Puzzle p) {
        puzzle=p;
    }
    public String getScramble(){
        scramble= puzzle.generateScramble();
        return scramble;
    }
    public File getPuzzleImage(){
        return puzzleImage;
    }
    public void drawScramble(){
        Long mm=System.currentTimeMillis();
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYYMMDDhhmmss");
        String now=dateFormat.format(date);
        puzzleImage=new File(now+mm.toString()+".png");

        Svg svg=null;
        InputStream in = null;
        OutputStream out = null;
        try {
            svg= puzzle.drawScramble(scramble,puzzle.getDefaultColorScheme());

            in=new ByteArrayInputStream(svg.toString().getBytes());
            out = new FileOutputStream(puzzleImage);
            out = new BufferedOutputStream(out);

            Transcoder transcoder = new PNGTranscoder();
            TranscoderInput input = new TranscoderInput(in);
            TranscoderOutput output = new TranscoderOutput(out);
            transcoder.transcode(input, output);
        }catch (InvalidScrambleException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (TranscoderException e) {
        e.printStackTrace();
        }finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
